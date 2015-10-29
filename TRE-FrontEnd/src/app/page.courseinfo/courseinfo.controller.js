(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CourseInfoController', CourseInfoController);

    /** @ngInject */
    function CourseInfoController($scope, $stateParams, courseAPI) {
        var vm = this;

        vm.courseInfo = {};

        vm.getShortInfo = getShortInfo;
        vm.saveAttachments = saveAttachments;
        vm.saveComments = saveComments;
        vm.saveParticipants = saveParticipants;
        vm.saveTimetable = saveTimetable;

        $scope.courseInfo = vm.courseInfo;

        vm.getShortInfo();

        function getShortInfo() {
            courseAPI.getShortInfo($stateParams.courseId).then(function(data) {
                $scope.courseInfo = angular.copy(data);
                console.log($scope.courseInfo);
            });
        }

        function saveAttachments(attachments) {
            vm.courseInfo.attachments = angular.copy(attachments);
        }

        function saveComments(comments) {
            vm.courseInfo.comments = angular.copy(comments);
        }

        function saveParticipants(participants) {
            vm.courseInfo.participants = angular.copy(participants);
        }

        function saveTimetable(timetable) {
            vm.courseInfo.lessonList = angular.copy(timetable);
        }

    }
})();