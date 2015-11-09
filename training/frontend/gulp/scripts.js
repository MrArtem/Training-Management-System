'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');
var webserver = require('gulp-webserver');

var $ = require('gulp-load-plugins')();

gulp.task('webserver', function () {
    console.log("webserver task running");
    gulp.src(conf.paths.tmp)
        .pipe(webserver({
            port: 3001,
            proxies: [
                {
                    source: '/', target: 'http://localhost:8080'
                }
            ]
        }));
});

gulp.task('scripts', function () {
    return gulp.src(path.join(conf.paths.src, '/app/**/*.js'))
        .pipe($.jshint())
        .pipe($.jshint.reporter('jshint-stylish'))
        .pipe(browserSync.reload({stream: true}))
        .pipe($.size())
});
