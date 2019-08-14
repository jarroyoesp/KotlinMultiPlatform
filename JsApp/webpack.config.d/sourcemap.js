config.module.rules.push({
    enforce: 'pre',
    test: /\.js$/,
    loader: 'source-map-loader'
});
config.devtool = "eval-source-map";
