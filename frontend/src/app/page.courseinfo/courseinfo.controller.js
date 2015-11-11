(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CourseInfoController', CourseInfoController);

    /** @ngInject */
    function CourseInfoController($scope, $state, $stateParams, courseAPI, $location) {
        var vm = this;

        vm.courseInfo = {};
        vm.courseInfo.files = [];
        vm.courseInfo.commentList = [];
        vm.courseInfo.participantsList = [];
        vm.courseInfo.timetable = [];
        vm.isContentLoaded = false;
        vm.rating = 0;

        vm.getLanguage = getLanguage;
        vm.getShortInfo = getShortInfo;
        vm.isActive = isActive;
        vm.leave = leave;
        vm.setRating = setRating; //TODO
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

        function leave() {
            courseAPI.leave($stateParams.courseId).then(function(data) {
                vm.courseInfo.canSubscribe = true;
                console.log('Left training successfully');
            })
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
                vm.courseInfo.canSubscribe = false;
                console.log('Subscribed successfully');
            })
        }

        function isActive(state) {
            return $location.absUrl().search(state) === -1 ? false : true
        }

    }
})();