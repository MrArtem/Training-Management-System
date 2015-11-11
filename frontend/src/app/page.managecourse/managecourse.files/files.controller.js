(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('FilesController', FilesController);

    /** @ngInject */
    function FilesController($scope, $stateParams, userAPI) {
        var vm = this;
    }
})();