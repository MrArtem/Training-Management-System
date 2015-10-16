(function () {
    'use strict';
        angular
            .module('tmsAPI')
            .factory('urlProvider', UrlProvider);

    /* @ngInject */
    function UrlProvider() {
        var urlProvider = {
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            confirm: confirm,
            createCourse: createCourse,
            editCourse: editCourse,
            getApproveList: getApproveList,
            getCoursesForUser: getCoursesForUser,
            getCurrentCoursesForUser: getCurrentCoursesForUser,
            getEditedCourse: getEditedCourse,
            getFeedbacksOnUser: getFeedbacksOnUser,
            getPastCoursesForUser: getPastCoursesForUser,
            getProfileInfo: getProfileInfo,
            getWaitingCoursesForUser: getWaitingCoursesForUser,
            login: login,
            logout: logout
        };
        return urlProvider;
    }

    function cancelCreate(trainingId) {
        return '/training/cancel_create/' + trainingId;
    }

    function cancelEdit(trainingId) {
        return '/training/cancel_edit/' + trainingId;
    }

    function confirm(trainingId) {
        return '/training/confirm/' + trainingId;
    }

    function createCourse() {
        return '/training/create';
    }

    function editCourse(trainingId) {
        return '/training/edit/' + trainingId;
    }

    function getApproveList() {
        //return ;
    }
    function getCoursesForUser(login) {
        //return;
    }

    function getCurrentCoursesForUser(userId) {
        //return;
    }

    function getEditedCourse(trainingId) {
        return '/training/get_edited_course/' + trainingId;
    }

    function getFeedbacksOnUser(userId) {
        //return;
    }

    function getPastCoursesForUser(userId) {
        //return;
    }

    function getProfileInfo(userId) {
        //return;
    }

    function getWaitingCoursesForUser(userId) {
        //return;
    }

    function login() {
        return '/api/login';
    }

    function logout() {
        return '/api/logout';
    }
})();