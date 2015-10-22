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
        function NavbarController(authService, searchAPI) {
            var vm = this;
            vm.foundTrainings = [];
            vm.foundUsers = [];
            vm.searchAnswer = '';

            vm.isAdmin = isAdmin;
            vm.clearModal = clearModal;
            vm.getUsername = getUsername;
            vm.getUserId = getUserId;
            vm.logout = logout;
            vm.search = search;

            vm.username = getUsername();
            vm.id = getUserId();
            console.log(vm.id);

            //////

            function clearModal() {
                vm.foundTrainings = [];
                vm.foundUsers = [];
                vm.searchAnswer = '';
                vm.searchQuery = '';
            }

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

            function search() {
                if (vm.searchQuery.length == 0) {
                    vm.searchAnswer = "";
                    vm.foundTrainings = [];
                    vm.foundUsers = [];
                    return;
                }
                if (vm.searchQuery.length < 3) {
                    vm.searchAnswer = "Please, specify your query";
                    vm.foundTrainings = [];
                    vm.foundUsers = [];
                    return;
                }
                if(vm.isAdmin()) {
                    searchAPI.findTrainings(vm.searchQuery).then(function(data) {
                        vm.foundTrainings = angular.copy(data);
                        vm.searchAnswer = (data.length == 0) ? 'no trainings found' : '';
                    });
                    searchAPI.findUsers(vm.searchQuery).then(function(data) {
                        vm.foundUsers = angular.copy(data);
                        vm.searchAnswer = (data.length == 0) ? 'no users found' : '';
                    });
                }
                else {
                    searchAPI.findTrainings(vm.searchQuery).then(function(data) {
                        vm.foundTrainings = angular.copy(data);
                        vm.searchAnswer = (data.length == 0) ? 'no trainings found' : '';
                    });
                }
            }

        }
    }

})();