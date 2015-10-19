(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('TimetableController', TimetableController);

    /** @ngInject */
    function TimetableController($scope, $modal, courseAPI) {
        var vm = this;
        vm.addLesson = addLesson;
        vm.deleteLesson = deleteLesson;
        vm.editLesson = editLesson;
        vm.getTimetable = getTimetable;

        vm.getTimetable();

        function addLesson(newDate, newPlace) {
            //courseAPI.addLesson(newDate, newPlace).then(function () {
            //    vm.getTimetable();
            //});
        }

        function deleteLesson(lessonId) {
            //courseAPI.deleteLesson(lessonId).then(function() {      //maybe change without calling server?
            //    vm.getTimetable();
            //});
        }

        function editLesson(lessonId, newDate, newPlace) {
            //courseAPI.editLesson(lessonId, newDate, newPlace).then(function(data) {     //maybe change without calling server?
            //    vm.getTimetable();
            //});
        }

        function getTimetable() {
            //courseAPI.getTimetable().then(function (data) {
            //        $scope.$parent.saveTimetable(data);
            //    }
            //);
        }
    }

})();