(function () {
    'use strict';
    angular
        .module('tmsApp')
        .factory('authService', authService);

    /* @ngInject */
    function authService($cookies, $state, $q, $window, authAPI) {
        var loginPromise = $q.defer();
        var accessRights = {
            accessRightsFromString: accessRightsFromString,
            admin: 0,
            user: 1,
            exCoach: 2,
            exUser: 3,
            noRights: 4
        };

        var isLogged = false;
        var user = {
            accessRights: 4,
            login: "",
            username: ""
        };

        var authService = {
            credsLogin: credsLogin,
            getAccessRights: getAccessRights,
            getLoginPromise: getLoginPromise,
            getUser: getUser,
            isLogged: getIsLogged,
            login: login,
            logout: logout
        };

        return authService;

        //functions

        function getIsLogged() {
            return isLogged;
        }

        function login(login, password, isRemember) {
            //Perform Server log in

            authAPI.login(login, password).then(function (data) {
                // success :
                console.log(data);
                isLogged = true;
                user.username = data.username;
                user.userId = data.userId;
                user.accessRights = accessRights.accessRightsFromString(data.accessRights);
                if (isRemember) {
                    putUserCreds(user);
                }
                loginPromise.resolve(user);
                $state.go('mycourses');
            }, function () {

            });

            return true;

            //error:
            //return error promise

        }

        function logout() {
            // server logout
            // just logout
            isLogged = false;
            user = {};
            clearUserCreds();
            $window.location.reload();
            //$state.go('login');
        }

        function credsLogin() {
            var userCreds = getUserCreds();
            if (userCreds) {
                user = userCreds;
                isLogged = true;
                loginPromise.resolve(user);
            }
        }

        function getUser() {
            return angular.copy(user);
        }

        function getLoginPromise() {
            return loginPromise.promise;
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