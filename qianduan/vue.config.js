const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  devServer: {
    host: 'localhost',
    port : '8080',
    hot: true,
    open: true,

    proxy: {
      '/api': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
