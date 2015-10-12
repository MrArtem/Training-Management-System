(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('MyCoursesController', MyCoursesController);

    /** @ngInject */
    function MyCoursesController() {
        var vm = this;
        vm.filter = {isCoach: "", search: ""};
        

    }
})();