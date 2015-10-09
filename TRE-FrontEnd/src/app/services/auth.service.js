(function () {
    'use strict';
    angular
        .module('tmsApp')
        .factory('authService', authService);

    /* @ngInject */
    function authService($cookies, $state, authAPI) {
        var accessRights = {
            admin: 0,
            user: 1,
            exCoach: 2,
            exUser: 3,
            noRights: 4,
            accessRightsFromString: accessRightsFromString
        };


        var isLogged = false;
        var user = {
            login: "",
            username: "",
            accessRights: 4,
        };


        var authService = {
            login: login,
            credsLogin: credsLogin,
            logout: logout,
            isLogged: getIsLogged,
            getUser: getUser,
            getAccessRights: getAccessRights
        };

        return authService;

        //functions

        function getIsLogged() {
            return isLogged
        }

        function login(login, password, isRemember) {
            //Perform Server log in

            authAPI.login(login, password).then(function (data) {
                // success :
                isLogged = true;
                user.login = data.login;
                user.username = data.username;
                user.accessRights = accessRights.accessRightsFromString(data.accessRights);
                if (isRemember) {
                    putUserCreds(user);
                }
                $state.go('mycourses');

            }, function(){

            })



            

            return true;
            //return success promise

            //error:
            //return error promise


        }

        function credsLogin() {
            var userCreds = getUserCreds()
            if (userCreds) {
                user = userCreds;
                isLogged = true;
            }
        }

        function logout() {
            // server logout
            // just logout
            isLogged = false;
            user = {};
            clearUserCreds();
            $state.go('login');
        }

        function getUser() {
            return angular.copy(user);
        }

        function getAccessRights() {
            return user.accessRights;
        }

        function accessRightsFromString(accessString) {

            if (accessString === "ADMIN")
                return this.admin;
            if (accessString === "USER")
                return this.user;
            if (accessString === "EX_COACH")
                return this.exCoach;
            if (accessString === "EX_USER")
                return this.exUser;

            return this.noRights;
        }

        function putUserCreds(user) {
            $cookies.putObject('user', user);
        }

        function getUserCreds() {
            return $cookies.getObject('user');
        }

        function clearUserCreds() {
            return $cookies.remove('user');
        }



    }

})();