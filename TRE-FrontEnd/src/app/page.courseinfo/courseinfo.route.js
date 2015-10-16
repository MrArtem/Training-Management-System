(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        var modalInstance;
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
            }).state('courseinfo.timetable.editlesson', {
                url: '/editlesson',
                onEnter: function ($modal, $stateParams) {
                    modalInstance = $modal.open({
                        templateUrl: 'app/page.courseinfo/courseinfo.timetable/editlesson.modal.html',
                        controller: 'ModalEditLessonController',
                        controllerAs: 'editlesson',
                        resolve: {
                            index: function () {
                                return $stateParams.lessonId;
                            }
                        }
                    }).result.finally(function () {
                            console.log('finally clause');
                            modalInstance = null;
                            if ($state.$current.name === 'courseinfo.timetable.editlesson') {
                                $state.go('^');
                            }
                        });
                    ;
                },
                onExit: function() {
                    if (modalInstance) {
                        modalInstance.close();
                    }
                }
            })
        ;

        $urlRouterProvider.otherwise('/');
    }

})();
