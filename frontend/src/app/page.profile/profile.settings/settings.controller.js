(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('SettingsController', SettingsController);

    /** @ngInject */
    function SettingsController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.changePassword = changePassword;
        vm.changePhoneNumber = changePhoneNumber;

        function changePassword() {
        }

        function changePhoneNumber() {

        }
    }
})();