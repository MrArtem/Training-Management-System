(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('profile.attended', {
                url: '/attended',
                templateUrl: 'app/page.profile/profile.attended/attended.html',
                controller: 'AttendedCoursesController',
                controllerAs: 'attendedcourses'
            }).state('profile.current', {
                url: '/current',
                templateUrl: 'app/page.profile/profile.current/current.html',
                controller: 'CurrentCoursesController',
                controllerAs: 'currentcourses'
            }).state('profile.waiting', {
                url: '/waiting',
                templateUrl: 'app/page.profile/profile.waiting/waiting.html',
                controller: 'WaitingCoursesController',
                controllerAs: 'waitingcourses'
            }).state('profile.feedbacks', {
                url: '/feedbacks',
                templateUrl: 'app/page.profile/profile.feedbacks/feedbacks.html',
                controller: 'FeedbacksController',
                controllerAs: 'feedbacks'
            })
        ;

        $urlRouterProvider.otherwise('/');
    }

})();
