(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('LoginController', LoginController);

    /** @ngInject */
    function LoginController(authService, $state) {
        var vm = this;
        vm.submit = submit;
            
        ////
        
        function submit(){
            authService.login(vm.login, vm.password, vm.isRemember);
        }


    }
})();
