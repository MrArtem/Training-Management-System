(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('SettingsController', SettingsController);

    /** @ngInject */
    function SettingsController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.changePassword = changePassword;
        vm.newPassword = "";

        function changePassword() {
            userAPI.changePassword($stateParams.userId, vm.newPassword).then(function(result) {
                return result.data;
                console.log('Password changed successfully');
            });
        }
    }
})();