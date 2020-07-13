process.env.CHROME_BIN = require('puppeteer').executablePath()

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine', 'browserify'],
        files: [
            'src/**/*.spec.js'
        ],
        exclude: [],
        preprocessors: {
            'src/**/*.spec.js': ['browserify']
        },
        reporters: ['progress'],

        colors: true,
        logLevel: config.LOG_INFO,
        autoWatch: false,
        browsers: ['ChromeHeadless'],
        singleRun: true,
        port: 4080,
        concurrency: Infinity
    })
}
