(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('TimetableController', TimetableController);

    /** @ngInject */
    function TimetableController($scope, $stateParams, courseAPI) {
        var vm = this;
        vm.attendanceList = [];

        vm.addLesson = addLesson;
        vm.deleteLesson = deleteLesson;
        vm.editLesson = editLesson;
        vm.getAttendance = getAttendance;
        vm.getLessonId = getLessonId;
        vm.getTimetable = getTimetable;
        vm.setAttendance = setAttendance;

        vm.getTimetable();

        function addLesson() {
            courseAPI.addLesson($stateParams.courseId, vm.newDate, vm.newPlace).then(function (data) {
                console.log('lesson added successfully');
            });
        }

        function deleteLesson() {
            courseAPI.deleteLesson($stateParams.courseId, vm.editedLessonId).then(function() {      //maybe change without calling server?
                console.log('lesson deleted successfully');
            });
        }

        function editLesson() {
            console.log(vm.editedLessonId +  vm.editedDate + vm.editedPlace);
            courseAPI.editLesson($stateParams.courseId, vm.editedLessonId, vm.editedDate, vm.editedPlace).then(function (data) {     //maybe change without calling server?
                console.log('lesson edited successfully');
            });
        }

        function getAttendance(index) {
            vm.getLessonId(index);
            courseAPI.getAttendance(vm.editedLessonId).then(function(data) {
                vm.attendanceList = angular.copy(data);
                console.log('Received attendance: ', data);
            });
        }

        function getLessonId(index) {
            vm.editedLessonId = $scope.$parent.courseInfo.lessonList[index].id;
            console.log(vm.editedLessonId);
        }

        function getTimetable() {
            courseAPI.getTimetable($stateParams.courseId).then(function (data) {
                    $scope.$parent.courseInfo.lessonList = angular.copy(data);
                    console.log(data);
                }
            );
        }

        function setAttendance() {
            console.log('Attendance to be set: ', vm.attendanceList);
            courseAPI.setAttendance($stateParams.courseId, vm.editedLessonId, vm.attendanceList).then(function(data) {
                console.log('Attendance set successfully');
            })
        }
    }

})();