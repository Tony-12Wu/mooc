const CompressionPlugin = require("compression-webpack-plugin")

module.exports = {
    //基本路径
    publicPath: process.env.NODE_ENV === 'prod' ? '/web/' : '/',
    // 构建时的输出目录
    outputDir: "dist",
    // 放置静态资源的目录
    assetsDir: "static",
    //html输出路径
    indexPath: "index.html",
    //文件名哈希
    filenameHashing: true,
    //关闭大断点
    productionSourceMap: false,
    configureWebpack: {
        plugins: [
            new CompressionPlugin({
                test: /\.(js|css)(\?.*)?$/i,//需要压缩的文件正则
                threshold: 10240,//文件大小大于这个值时启用压缩
                deleteOriginalAssets: false//压缩后保留原文件
            })
        ],

    }
}