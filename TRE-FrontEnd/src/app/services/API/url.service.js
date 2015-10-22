(function () {
    'use strict';
        angular
            .module('tmsAPI')
            .factory('urlProvider', UrlProvider);

    /* @ngInject */
    function UrlProvider() {
        var urlProvider = {
            addParticipant: addParticipant,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            confirm: confirm,
            createCourse: createCourse,
            editCourse: editCourse,
            findAnything: findAnything,
            findTrainings: findTrainings,
            findUsers: findUsers
            getApproveList: getApproveList,
            getCoursesForUser: getCoursesForUser,
            getCurrentCoursesForUser: getCurrentCoursesForUser,
            getEditedCourse: getEditedCourse,
            getFeedbacksOnUser: getFeedbacksOnUser,
            getParticipants: getParticipants,
            getPastCoursesForUser: getPastCoursesForUser,
            getProfileInfo: getProfileInfo,
            getTimetable: getTimetable,
            getWaitingCoursesForUser: getWaitingCoursesForUser,
            login: login,
            logout: logout,
            manageLesson: manageLesson
        };
        return urlProvider;
    }

    function addParticipant(courseId) {
        return '/api/training/' + courseId + '/add_listener';
    }

    function cancelCreate(trainingId) {
        return '/api/training/cancel_create/' + trainingId;
    }

    function cancelEdit(trainingId) {
        return '/api/training/cancel_edit/' + trainingId;
    }

    function confirm(trainingId) {
        return '/api/training/confirm/' + trainingId;
    }

    function createCourse() {
        return '/api/training/create';
    }


    function editCourse(trainingId) {
        return '/api/training/edit/' + trainingId;
    }

    function findAnything() {

    }

    function findTrainings() {

    }

    function findUsers() {

    }

    function getApproveList() {
        //return ;
    }
    function getCoursesForUser() {
        return '/api/training/training_list';
    }

    function getCurrentCoursesForUser(userId) {
        return '/api/user_controller/actualTraining/' + userId;
    }

    function getEditedCourse(trainingId) {
        return '/api/training/get_edited_course/' + trainingId;
    }

    function getFeedbacksOnUser(userId) {
        //return;
    }

    function getParticipants(courseId) {
        return '/api/training/' + courseId + '/get_listeners';
    }

    function getPastCoursesForUser(userId) {
        return '/api/user_controller/visitedTraining/' + userId;
    }

    function getProfileInfo(userId) {
        return '/api/user_controller/user_info/' + userId;
    }

    function getTimetable(courseId) {
        return '/api/training/' + courseId + '/get_lessons';
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

    function manageLesson(courseId) {
        return '/api/training/' + courseId + '/lessons';
    }
})();