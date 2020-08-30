const path = require('path')
const webpack = require('webpack')

module.exports = {
    entry: {
        bundle: './index.js'
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        publicPath: '/dist/',
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
            }
        ]
    }
}