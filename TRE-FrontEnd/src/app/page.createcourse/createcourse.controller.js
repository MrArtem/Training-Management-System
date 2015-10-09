(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CreateCourseController', CreateCourseController);

    /** @ngInject */
    function CreateCourseController($scope) {
        var vm = this;
        vm.courseInfo = {};
        $scope.courseInfo = vm.courseInfo;
    }
})();