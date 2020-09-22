const path = require('path')
const {VueLoaderPlugin} = require('vue-loader')
const webpack = require('webpack')
const ts = require('typescript')
const environment = process.env.NODE_ENV || 'development';

const config = (env = {}) => ({
    mode: env.prod ? 'production' : 'development',
    devtool: '#source-map',
    devServer: {
        open: true,
        historyApiFallback: {
            index: 'index.html'
        }
    },
    output: {
        path: path.resolve(__dirname, '../resources/public'),
        filename: '[name].js',
    },
    entry: {
        bundle: './index.ts'
    },
    module: {
        rules: [
            {
                test: /\.ts$/,
                exclude: /node_modules|vue\/src/,
                use: [
                    {
                        loader: 'ts-loader',
                        options: {
                            appendTsSuffixTo: [/\.vue$/],
                            transpileOnly: true,
                        },
                    },
                ],
            },
            {
                test: /\.vue$/,
                use: 'vue-loader',
            },
        ]
    },
    resolve: {
        extensions: ['.ts', 'd.ts', '.tsx', '.js', '.vue'],
        alias: {
            '@userEnv': path.resolve(__dirname, `.env/${environment}.js`),
        },
    }
})

module.exports = config