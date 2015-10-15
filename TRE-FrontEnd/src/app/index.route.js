/* 
.state 
    .auth === true /* need to be loggedin to visit the page
    .accessRights === (0 to 4) 
        0 === admin rights only,
        1 === user+
        2 === exCoach+
        3 === exUser+
        4 === noRights
*/


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
                controllerAs: 'admin',
                auth: true,
                accessRights: 0
            }).state('browse', {
                url: '/browse',
                templateUrl: 'app/page.browse/browse.html',
                controller: 'BrowseController',
                controllerAs: 'browse',
                auth: true,
                accessRights: 1
            }).state('courseinfo', {
                url: '/courseinfo',
                templateUrl: 'app/page.courseinfo/courseinfo.html',
                controller: 'CourseInfoController',
                controllerAs: 'courseinfo',
                auth: true,
                accessRights: 2
            }).state('login', {
                url: '/login',
                templateUrl: 'app/page.login/login.html',
                controller: 'LoginController',
                controllerAs: 'login',
                accessRights: 4,
                auth: false
            }).state('managecourse', {
                abstract: true,
                url: '/managecourse',
                templateUrl: 'app/page.managecourse/managecourse.html',
                controller: 'ManageCourseController',
                controllerAs: 'managecourse',
                params: {
                    courseId: null,
                    type: null
                },
                auth: true,
                accessRights: 2
            }).state('mycourses', {
                url: '/',
                templateUrl: 'app/page.mycourses/mycourses.html',
                controller: 'MyCoursesController',
                controllerAs: 'mycourses',
                auth: true,
                accessRights: 2
            }).state('profile', {
                url: '/profile',
                templateUrl: 'app/page.profile/profile.html',
                controller: 'ProfileController',
                controllerAs: 'profile',
                auth: true,
                accessRights: 2
            })

        ;

        $urlRouterProvider.when('/createcourse', '/createcourse/step1');
        $urlRouterProvider.otherwise('/');
    }

})();