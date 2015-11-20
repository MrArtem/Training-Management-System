(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('LoginController', LoginController);

    /** @ngInject */
    function LoginController($state, authService) {
        var vm = this;
        vm.submit = submit;
        vm.loginError = false;
        
        
        function submit(){
            vm.loginError = false;
            authService.login(vm.username, vm.password, vm.isRemember).catch(function(error) {
                vm.loginError = true;
            });
        }
    }
})();
