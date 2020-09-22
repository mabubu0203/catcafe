const path = require('path')
const compiler = require('@riotjs/compiler');
const ts = require('typescript')
const environment = process.env.NODE_ENV || 'development';

compiler.registerPreprocessor('javascript', 'ts', (code, options) => {
    const {file} = options;

    const result = ts.transpileModule(code, {
        fileName: file,
        compilerOptions: {
            module: ts.ModuleKind.ESNext
        }
    })

    return {
        code: result.outputText,
        map: null
    }
})

module.exports = {
    mode: 'development',
    entry: {
        bundle: './index.js'
    },
    output: {
        path: path.resolve(__dirname, '../resources/public'),
        filename: '[name].js',
    },
    devtool: '#source-map',
    devServer: {
        open: true,
        historyApiFallback: {
            index: 'index.html'
        }
    },
    module: {
        rules: [
            {
                test: /\.riot$/,
                exclude: /node_modules/,
                use: [
                    {
                        loader: '@riotjs/webpack-loader',
                        options: {
                            hot: true
                        }
                    }
                ]
            },
            {
                test: /\.ts$/,
                exclude: /node_modules/,
                use: 'ts-loader',
            }
        ]
    },
    resolve: {
        extensions: ['.ts', '.riot', '.js'],
        alias: {
            '@userEnv': path.resolve(__dirname, `.env/${environment}.js`),
        },
    }
}