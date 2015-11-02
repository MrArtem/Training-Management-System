(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CourseInfoController', CourseInfoController);

    /** @ngInject */
    function CourseInfoController($scope, $stateParams, courseAPI) {
        var vm = this;

        vm.courseInfo = {};
        vm.courseInfo.files = [];
        vm.courseInfo.commentList = [];
        vm.courseInfo.participantsList = [];
        vm.courseInfo.timetable = [];
        vm.isContentLoaded = false;

        vm.getLanguage = getLanguage;
        vm.getShortInfo = getShortInfo;
        vm.leave = leave;
        vm.subscribe = subscribe;

        $scope.courseInfo = vm.courseInfo;

        vm.getShortInfo();

        function getLanguage(lang) {
            switch(lang) {
                case 1:
                    return 'English';
                case 2:
                    return 'Russian';
                default:
                    return 'unknown';
            }
        }

        function getShortInfo() {
            courseAPI.getShortInfo($stateParams.courseId).then(function(data) {
                $scope.courseInfo = angular.copy(data);
                vm.isContentLoaded = true;
                console.log($scope.courseInfo);
            });
        }

        function subscribe() {
            courseAPI.subscribe($stateParams.courseId).then(function(data) {
                console.log('Subscribed successfully');
            })
        }

        function leave() {
            courseAPI.leave($stateParams.courseId).then(function(data) {
                console.log('Left training successfully');
            })
        }

    }
})();