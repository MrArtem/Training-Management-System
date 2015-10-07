(function(){
    'use strict';
    angular
        .module('tmsAPI')
        .factory('courseAPI', courseAPI);
    
    /* @ngInject */
    function courseAPI(){
        var courseAPI = {
            addLesson: addLesson,
            deleteLesson: deleteLesson,
            getAttachments: getAttachments,
            getComments: getComments,
            getParticipants: getParticipants,
            getShortInfo: getShortInfo,
            getTimetable: getTimetable,
            editLesson: editLesson
        }
        return courseAPI;

        //////////

        function getAttachments() {

        }

        function getComments() {

        }

        function getParticipants() {

        }

        function getShortInfo() {

        }

        function getTimetable() {

        }

        //////////

        function addLesson(newdDate, newPlace) {

        }

        function editLesson(lessonId, newDate, newPlace) {

        }

        function deleteLesson(lessonId) {

        }

    }
    
})();