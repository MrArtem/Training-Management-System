(function () {
    'use strict';

    angular
        .module('tmsApp')
        .directive('navBar', navBar);

    /** @ngInject */
    function navBar() {
        var directive = {
            restrict: 'E',
            templateUrl: 'app/components/navbar/navbar.html',
            controller: NavbarController,
            controllerAs: 'vm',
            bindToController: true
        };

        return directive;

        /** @ngInject */
        function NavbarController(authService) {
            var vm = this;
            vm.logout = logout;

            
            //////
            
            function logout(){
                authService.logout();
            }


        }
    }

})();