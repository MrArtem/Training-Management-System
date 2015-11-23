(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CourseInfoController', CourseInfoController);

    /** @ngInject */
    function CourseInfoController($scope, $stateParams, $location, courseAPI, authService) {
        var vm = this;

        vm.courseInfo = {};
        vm.courseInfo.files = [];
        vm.courseInfo.commentList = [];
        vm.courseInfo.participantsList = [];
        vm.courseInfo.timetable = [];
        vm.isContentLoaded = false;
        vm.rating = 0;

        vm.deleteCourse = deleteCourse;
        vm.getLanguage = getLanguage;
        vm.getShortInfo = getShortInfo;
        vm.isActive = isActive;
        vm.isAdmin = isAdmin;
        vm.leave = leave;
        vm.setRating = setRating; //TODO
        vm.subscribe = subscribe;

        $scope.courseInfo = vm.courseInfo;

        vm.getShortInfo();

        function deleteCourse() {
            courseAPI.deleteCourse($stateParams.courseId).then(function(data) {
                //
            });
        }

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

        function leave() {
            courseAPI.leave($stateParams.courseId).then(function(data) {
                $scope.courseInfo.canSubscribe = true;
                console.log('Left training successfully');
            });
        }

        function setRating(rating) {
            console.log('Rating to be set: ', rating);
            if($scope.courseInfo.canRate) {
                courseAPI.setRating($stateParams.courseId, rating).then(function(data) {
                    $scope.courseInfo.rating = data.rating;
                    console.log('Training rated successfully');
                });
            }
        }

        function subscribe() {
            courseAPI.subscribe($stateParams.courseId).then(function(data) {
                $scope.courseInfo.canSubscribe = false;
                console.log('Subscribed successfully');
            });
        }

        function isActive(state) {
            return $location.absUrl().search(state) === -1 ? false : true;
        }

        function isAdmin() {
            return authService.getAccessRights() == 0 ? true : false;
        }

    }
})();