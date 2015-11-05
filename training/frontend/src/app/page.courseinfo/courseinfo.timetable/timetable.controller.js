(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('TimetableController', TimetableController);

    /** @ngInject */
    function TimetableController($scope, $stateParams, courseAPI) {
        var vm = this;

        vm.addLesson = addLesson;
        vm.deleteLesson = deleteLesson;
        vm.editLesson = editLesson;
        vm.getLessonId = getLessonId;
        vm.getTimetable = getTimetable;

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
    }

})();