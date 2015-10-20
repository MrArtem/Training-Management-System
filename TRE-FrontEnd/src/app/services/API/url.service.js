(function () {
    'use strict';
        angular
            .module('tmsAPI')
            .factory('urlProvider', UrlProvider);

    /* @ngInject */
    function UrlProvider() {
        var urlProvider = {
            addLesson: addLesson,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            confirm: confirm,
            createCourse: createCourse,
            deleteLesson: deleteLesson,
            editCourse: editCourse,
            editLesson: editLesson,
            getApproveList: getApproveList,
            getCoursesForUser: getCoursesForUser,
            getCurrentCoursesForUser: getCurrentCoursesForUser,
            getEditedCourse: getEditedCourse,
            getFeedbacksOnUser: getFeedbacksOnUser,
            getPastCoursesForUser: getPastCoursesForUser,
            getProfileInfo: getProfileInfo,
            getTimetable: getTimetable,
            getWaitingCoursesForUser: getWaitingCoursesForUser,
            login: login,
            logout: logout
        };
        return urlProvider;
    }

    function addLesson(courseId) {
        return '/training/' + courseId + '/add_lesson';
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

    function deleteLesson(courseId, lessonId) {
        return '/training/' + courseId + '/delete_lesson/' + lessonId;
    }

    function editCourse(trainingId) {
        return '/training/edit/' + trainingId;
    }

    function editLesson(trainingId) {
        return '/training/' + trainingId + '/edit_lesson';
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

    function getTimetable(courseId) {
        return '/training/' + courseId + '/get_lessons';
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