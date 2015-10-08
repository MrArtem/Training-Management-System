(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('admin', {
                url: '/admin',
                templateUrl: 'app/page.admin/admin.html',
                controller: 'AdminController',
                controllerAs: 'admin'
            }).state('browse', {
                url: '/browse',
                templateUrl: 'app/page.browse/browse.html',
                controller: 'BrowseController',
                controllerAs: 'browse'
            }).state('courseinfo', {
                url: '/courseinfo',
                templateUrl: 'app/page.courseinfo/courseinfo.html',
                controller: 'CourseInfoController',
                controllerAs: 'courseinfo'
            }).state('createcourse', {
                abstract: true,
                url: '/createcourse',
                templateUrl: 'app/page.createcourse/createcourse.html',
                controller: 'CreateCourseController',
                controllerAs: 'createcourse'
            }).state('mycourses', {
                url: '/',
                templateUrl: 'app/page.mycourses/mycourses.html',
                controller: 'MyCoursesController',
                controllerAs: 'mycourses'
            })
        ;

        $urlRouterProvider.when('/createcourse', '/createcourse/step1');
        $urlRouterProvider.otherwise('/');
    }

})();
