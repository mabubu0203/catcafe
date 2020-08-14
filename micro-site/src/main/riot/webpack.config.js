const path = require('path')
const webpack = require('webpack')

module.exports = {
    entry: {
        welcome: './welcome.js',
        stored: './stored.js'
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
            index: 'welcome.html'
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