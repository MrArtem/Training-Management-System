(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('courseinfo.attachments', {
                url: '/attachments',
                templateUrl: 'app/page.courseinfo/courseinfo.attachments/attachments.html',
                controller: 'AttachmentsController',
                controllerAs: 'attachments'
            }).state('courseinfo.comments', {
                url: '/comments',
                templateUrl: 'app/page.courseinfo/courseinfo.comments/comments.html',
                controller: 'CommentsController',
                controllerAs: 'comments'
            }).state('courseinfo.description', {
                url: '/description',
                templateUrl: 'app/page.courseinfo/courseinfo.description/description.html',
                controller: 'DescriptionController',
                controllerAs: 'description'
            }).state('courseinfo.participants', {
                url: '/participants',
                templateUrl: 'app/page.courseinfo/courseinfo.participants/participants.html',
                controller: 'ParticipantsController',
                controllerAs: 'participants'
            }).state('courseinfo.timetable', {
                url: '/timetable',
                templateUrl: 'app/page.courseinfo/courseinfo.timetable/timetable.html',
                controller: 'TimetableController',
                controllerAs: 'timetable'
            })
        ;

        $urlRouterProvider.otherwise('/');
    }

})();
