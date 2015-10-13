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
            getEditedCourse: getEditedCourse
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

    function getEditedCourse(trainingId) {
        return '/training/get_edited_course/' + trainingId;
    }
})();