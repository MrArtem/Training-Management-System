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
            controllerAs: 'navbar',
            bindToController: true
        };

        return directive;

        /** @ngInject */
        function NavbarController(authService) {
            var vm = this;
            vm.isAdmin = isAdmin;
            vm.logout = logout;
            vm.getUsername = getUsername;

            vm.username = getUsername();

            //////

            function getUsername() {
                return authService.getUser().username;
            }

            function isAdmin() {
                return authService.getAccessRights() == 0 ? true : false;
            }

            function logout() {
                authService.logout();
            }


        }
    }

})();