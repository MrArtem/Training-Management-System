'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');

var $ = require('gulp-load-plugins')();

var wiredep = require('wiredep').stream;
var _ = require('lodash');

var tmpSass = path.join(conf.paths.tmpSass,'/tmp-sass/');
 

gulp.task('styles',['styles-inject'], function () { 
    return $.rubySass([tmpSass],{sourcemap: true})
    .on('error', $.rubySass.logError)
    .pipe($.autoprefixer()).on('error', conf.errorHandler('Autoprefixer'))
    .pipe($.sourcemaps.write())
    .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve/app/')))
    .pipe(browserSync.reload({ stream: true }));
});

gulp.task('styles-inject', function(){
    var injectFiles = gulp.src([
        path.join(conf.paths.src, '/app/**/*.scss'),
        path.join('!' + conf.paths.src, '/app/index.scss')
    ], { read: false });

    var injectOptions = {
        transform: function(filePath) {
            //filePath = filePath.replace(conf.paths.src + '/app/', '');
            
            return '@import "' + '../../' + filePath + '";';
        },
        starttag: '// injector',
        endtag: '// endinjector',
        addRootSlash: false
    };
    
    return gulp.src(path.join(conf.paths.src, '/app/index.scss'))
        .pipe($.inject(injectFiles, injectOptions))
        .pipe(wiredep(_.extend({}, conf.wiredep)))
        .pipe(gulp.dest(tmpSass));
})



