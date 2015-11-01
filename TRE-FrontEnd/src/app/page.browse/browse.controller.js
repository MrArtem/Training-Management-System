(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('BrowseController', BrowseController);

    /** @ngInject */
    function BrowseController(courseAPI) {
        var vm = this;
        vm.courseList = [];

        vm.getTrainingList = getTrainingList;

        vm.getTrainingList();

        function getTrainingList() {
            //false stands for isActual
            courseAPI.getCourseList(false).then(function(data) {
                vm.courseList = angular.copy(data);
                console.log('Received courses: ');
                console.log(data);
            });
        }
    }
})();