(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('TimetableController', TimetableController)
        .controller('OpenEditModalController', OpenEditModalController)
        .controller('ModalEditLessonController', ModalEditLessonController)
        .controller('ModalAddLessonController', ModalAddLessonController);

    /** @ngInject */
    function OpenEditModalController($scope, $stateParams, $modal) {
        console.log('opening modal');
        var modalInstance = $modal.open({
            templateUrl: 'app/page.courseinfo/courseinfo.timetable/editlesson.modal.html',
            controller: 'ModalEditLessonController',
            resolve: {
                courseinfo: function () {
                    return $scope.$parent.courseInfo;
                },
                index: function () {
                    return $stateParams.lessonId;
                }
            }
        });

        modalInstance.result.then();
    }

    /** @ngInject */
    function TimetableController($scope, $modal, courseAPI) {
        var vm = this;
        vm.addLesson = addLesson;
        vm.deleteLesson = deleteLesson;
        vm.editLesson = editLesson;
        vm.getTimetable = getTimetable;
        vm.openEditLessonModal = openEditLessonModal;

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

        function openEditLessonModal(index) {
            console.log('open modal' + index);
            var modalInstance = $modal.open({
                templateUrl: 'app/page.courseinfo/courseinfo.timetable/editlesson.modal.html',
                controller: 'ModalEditLessonController',
                resolve: {
                    courseinfo: function () {
                        return $scope.$parent.courseInfo;
                    },
                    index: function () {
                        return index;
                    }
                }
            });

            modalInstance.result.then();
        }
    }

    /** @ngInject */
    function ModalAddLessonController($scope, $modalInstance, courseinfo) {

    }

    /** @ngInject */
    function ModalEditLessonController($state, $scope, $modalInstance, courseinfo, index) {
        console.log('opening modal');
        var vm = this;
        vm.courseinfo = courseinfo;
        vm.index = index;

        $scope.ok = function () {
            console.log('ok clicked');
            $modalInstance.close($scope.$parent.courseInfo.lessonList[vm.index], vm.index);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

    }

})();