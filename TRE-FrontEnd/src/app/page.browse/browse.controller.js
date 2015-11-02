(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('BrowseController', BrowseController);

    /** @ngInject */
    function BrowseController(courseAPI) {
        var vm = this;
        vm.courseList = [];
        vm.isContentLoaded = false;

        vm.getTrainingList = getTrainingList;

        vm.getTrainingList();

        function getTrainingList() {
            //false stands for isActual
            courseAPI.getCourseList(false).then(function(data) {
                vm.courseList = angular.copy(data);
                vm.isContentLoaded = true;
                console.log('Received courses: ');
                console.log(data);
            });
        }
    }
})();