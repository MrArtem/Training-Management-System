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
           if(authService.login(vm.username, vm.password, vm.isRemember))
               $state.go('mycourses');
        }


    }
})();
