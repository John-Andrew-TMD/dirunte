'use strict'
const { defineConfig } = require('@vue/cli-service')
const SpeedMeasurePlugin = require('speed-measure-webpack-plugin')
const CompressionPlugin = require('compression-webpack-plugin')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
const ParallelUglifyPlugin = require('webpack-parallel-uglify-plugin');
const smp = new SpeedMeasurePlugin()
const isProduction = process.env.NODE_ENV === 'production'
const path = require('path')
function resolve(dir) {
  return path.join(__dirname, dir)
}
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '/',
  outputDir: 'dist',
  assetsDir: 'static',
  parallel: true,
  lintOnSave: false,
  productionSourceMap: false,
  devServer: {
  	// 自动开启浏览器
  	open: false,
  	// 端口
  	port: 8060
	},
  configureWebpack: isProduction && smp.wrap({
    // output: {
    //   chunkFilename: 'js/chunck.[contenthash:8].js'
    // },
    plugins: [
      new BundleAnalyzerPlugin({
        analyzerMode: 'disabled',
        openAnalyzer: false
      }),
      new CompressionPlugin({
        test: /\.(js|html|css)$/,
        threshold: 10240,
        deleteOriginalAssets: false
      }),
      new ParallelUglifyPlugin({
        uglifyES: {
          output: {
            // 最紧凑的输出
            beautify: false,
            // 删除所有的注释
            comments: false
          },
          compress: {
            // 在UglifyJs删除没有用到的代码时不输出警告
            warnings: false,
            // 删除所有的 `debugger` 语句，可以兼容ie浏览器
            drop_debugger: true,
            // 删除所有的 `console` 语句，可以兼容ie浏览器
            drop_console: true,
            pure_funcs: ['console.log'],
            // 内嵌定义了但是只用到一次的变量
            collapse_vars: true
          }
        }
      })
    ],
    mode: "development",
    externals: {
      'vue': 'Vue',
      'vuex': 'Vuex',
      'vue-router': 'VueRouter',
      'axios': 'axios'
    } || {},
    optimization: {
      splitChunks: {
        chunks: 'all',
        // minChunks: 2,
        cacheGroups: {
          libs: {
            name: 'chunk-libs',
            test: /[\\/]node_modules[\\/]/,
            priority: 10,
            chunks: 'initial' // only package third parties that are initially dependent
          },
          echarts: {
            name: 'chunk-echarts',
            priority: 20,
            test: /[\\/]node_modules[\\/]_?echarts(.*)/
          },
          raphael: {
            name: 'chunk-raphael',
            priority: 20,
            test: /[\\/]node_modules[\\/]_?raphael(.*)/
          },
          commons: {
            name: 'chunk-commons',
            test: resolve('src/components'), // can customize your rules
            minChunks: 2, //  minimum common number
            priority: 5,
            reuseExistingChunk: true
          }
        }
      }
    }
  }) || {},
  chainWebpack: config => {
    config.plugin('define').tap(definitions => {
      Object.assign(definitions[0]['process.env'], {
        NODE_HOST: '"http://localhost:8888"',
      });
      return definitions;
    });
    config.plugin('html').tap(definitions => {
      definitions[0].title = "商城"
      return definitions;
    });
    config.resolve.alias
      .set('@', resolve('src'))
      .set('api', resolve('src/api'))
      .set('utils', resolve('src/utils'))
      .set('views', resolve('src/views'))
      .set('assets', resolve('src/assets'))
      .set('components', resolve('src/components'))
      .set('service', resolve('src/service'))
      .set('mixins', resolve('src/mixins'))
      .set('directives', resolve('src/directives'))
    config.plugins.delete('preload')
    config.plugins.delete('prefetch')

    config.module
      .rule('svg')
      .exclude.add(resolve('src/icons'))
      .end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()
  }
})
