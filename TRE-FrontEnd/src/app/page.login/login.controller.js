(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('LoginController', LoginController);

    /** @ngInject */
    function LoginController($state, authService) {
        var vm = this;
        vm.submit = submit;
            
        ////
        
        function submit(){
            authService.login(vm.username, vm.password, vm.isRemember);
        }


    }
})();
