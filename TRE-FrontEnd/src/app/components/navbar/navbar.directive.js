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

            vm.getUsername = getUsername;
            vm.getUserId = getUserId;
            vm.logout = logout;

            vm.username = getUsername();
            vm.id = getUserId();
            console.log(vm.id);

            //////

            function getUsername() {
                return authService.getUser().username;
            }

            function getUserId() {
                return authService.getUser().userId;
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