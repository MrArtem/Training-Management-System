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
        return '/api/training/' + courseId + '/add_lesson';
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

    function deleteLesson(courseId, lessonId) {
        return '/api/training/' + courseId + '/delete_lesson/' + lessonId;
    }

    function editCourse(trainingId) {
        return '/api/training/edit/' + trainingId;
    }

    function editLesson(trainingId) {
        return '/api/training/' + trainingId + '/edit_lesson';
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
        return '/api/training/get_edited_course/' + trainingId;
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
})();