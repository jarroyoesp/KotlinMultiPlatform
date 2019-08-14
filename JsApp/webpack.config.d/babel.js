config.module.rules.push({
    test: /\.jsx?$/,
    exclude: /node_modules/,
    use: {
        loader: 'babel-loader',
        options: {
            cacheDirectory: true,
        }
    }
});
