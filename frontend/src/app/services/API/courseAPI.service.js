(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('courseAPI', courseAPI);

    /* @ngInject */
    function courseAPI($state, $http, urlProvider) {
        var courseAPI = {
            addComment: addComment,
            addExCoach: addExCoach, //TODO
            addLesson: addLesson,
            addParticipant: addParticipant,
            addTag: addTag,
            approveCourse: approveCourse,
            approveLesson: approveLesson,
            cancelChange: cancelChange,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            cancelLesson: cancelLesson,
            createCourse: createCourse,
            deleteCourse: deleteCourse,
            deleteFile: deleteFile,
            deleteLesson: deleteLesson,
            deleteParticipant: deleteParticipant,
            editCourse: editCourse,
            getAllTags: getAllTags,
            getAttachments: getAttachments,
            getAttendance: getAttendance,
            getComments: getComments,
            getCourseList: getCourseList,
            getEditedCourse: getEditedCourse,
            getExCoachList: getExCoachList,
            getParticipants: getParticipants,
            getShortInfo: getShortInfo,
            getTimetable: getTimetable,
            editLesson: editLesson,
            leave: leave,
            setAttendance: setAttendance,
            setRating: setRating,
            subscribe: subscribe,
            uploadFiles: uploadFiles
        };
        return courseAPI;

        //////////

        function addTag(tag) {
            return $http.post(urlProvider.addTag(), tag).then(function(result) {
                return result.data;
            });
        }

        function getAllTags() {
            return $http.get(urlProvider.getAllTags()).then(function(result) {
                return result.data;
            });
        }

        //////////

        function getAttachments(courseId) {
            return $http.get(urlProvider.getAttachments(courseId)).then(function(result) {
                return result.data;
            });
        }

        function getAttendance(lessonId) {
            return $http.get(urlProvider.getAttendance(lessonId)).then(function(result) {
                return result.data;
            });
        }

        function getComments(courseId) {
            return $http.get(urlProvider.getComments(courseId)).then(function (result) {
                return result.data;
            });
        }

        function getExCoachList() {
            return $http.get(urlProvider.getExCoachList()).then(function(result) {
                return result.data;
            });
        }

        function getParticipants(courseId) {
            return $http.get(urlProvider.getParticipants(courseId)).then(function (result) {
                return result.data;
            });
        }

        function getShortInfo(courseId) {
            return $http.get(urlProvider.getShortInfo(courseId)).then(function(result) {
                return result.data;
            });
        }

        function getTimetable(courseId) {
            return $http.get(urlProvider.getTimetable(courseId)).then(function (result) {
                return result.data;
            });
        }

        //////////

        function approveCourse(actionId, courseData) {
            console.log(courseData);
            return $http.post(urlProvider.approveCourse(actionId), courseData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (results) {
                console.log('Course approved successfully!');
                $state.transitionTo('admin');
                return results.data;
            });
        }

        function approveLesson(actionId, lessonData) {
            return $http.put(urlProvider.approveLesson(actionId), lessonData).then(function(result) {
                return result.data;
            });
        }

        function createCourse(courseData) {
            console.log(courseData);
            return $http.post(urlProvider.createCourse(), courseData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (results) {
                console.log('Course created successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function cancelChange(actionId) {
            return $http.put(urlProvider.cancelChange(actionId)).then(function(result) {
                return result.data;
            });
        }

        function cancelCreate(courseId) {
            return $http.put(urlProvider.cancelCreate(courseId), {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course canceled successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function cancelEdit(courseId) {
            return $http.put(urlProvider.cancelEdit(courseId), {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course canceled successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function cancelLesson(actionId) {
            return $http.put(urlProvider.cancelLesson(actionId)).then(function(result) {
                return result.data;
            });
        }

        function deleteCourse(courseId) {
            return $http.delete(urlProvider.deleteCourse(courseId)).then(function(result) {
                console.log('Course deleted successfully!');
                $state.transitionTo('mycourses');
                return result.data;
            });
        }

        function editCourse(courseId, courseData) {
            return $http.put(urlProvider.editCourse(courseId), courseData).then(function(result) {
                console.log('Course edited successfully!');
                $state.transitionTo('mycourses');
                return result.data;
            });
        }

        function getEditedCourse(courseId) {
            return $http.get(urlProvider.getEditedCourse(courseId))
                .then(function (results) {
                    return results.data;
                });
        }

        //////////

        function addLesson(courseId, newDate, newPlace) {
            var lessonInfo = {
                prevLessonId: null,
                date: newDate.getTime(),
                place: newPlace
            };
            return $http.post(urlProvider.manageLesson(courseId), lessonInfo).then(function (result) {
                return result.data;
            });
        }

        function editLesson(courseId, lessonId, newDate, newPlace) {
            var lessonInfo = {
                prevLessonId: lessonId,
                date: newDate.getTime(),
                place: newPlace
            };
            return $http.put(urlProvider.manageLesson(courseId), lessonInfo).then(function (result) {
                return result.data;
            });
        }

        function deleteLesson(courseId, lessonId) {
            return $http.delete(urlProvider.deleteLesson(courseId, lessonId)).then(function (result) {
                return result.data;
            });
        }

        //////////

        function addParticipant(courseId, participantInfo) {
            return $http.post(urlProvider.addParticipant(courseId), participantInfo).then(function (result) {
                return result.data;
            });
        }

        function deleteParticipant(courseId, userId) {
            return $http.put(urlProvider.deleteParticipant(courseId, userId)).then(function(result) {
                return result.data;
            });
        }

        //////////

        function addComment(courseId, commentInfo) {
            return $http.post(urlProvider.addComment(courseId), commentInfo).then(function(result) {
                return result.data;
            });
        }

        function addExCoach(coachData) {
            return $http.post(urlProvider.addExCoach(), coachData).then(function(result) {
                return result.data;
            });
        }

        //////////

        function uploadFiles(courseId, files) {
            console.log(files);

            var initialValue = {};
            var fileMap = files.reduce(function(acc, file) {
                acc[file.name] = file.data;
                return acc;
            }, initialValue);
            console.log(fileMap);

            var formData = new FormData();
            formData.append('idTraining', courseId);
            formData.append('files', JSON.stringify(fileMap));

            console.log(formData.toString());
            return $http.post(urlProvider.uploadFiles(), formData, {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function(result) {
                return result.data;
            });
        }

        function deleteFile(fileId) {
           return $http.delete(urlProvider.deleteFile(fileId)).then(function(result) {
               return result.data;
           });
        }

        //////////

        function getCourseList(isActual, tagList) {
            console.log('Taglist: ', tagList);
            return $http.get(urlProvider.getCourseList(), {
                params: {
                    is_actual: isActual,
                    page: 1,
                    tag: tagList
                }
            }).then(function (results) {
                return results.data;
            });
        }

        function leave(courseId) {
            return $http.put(urlProvider.leave(courseId)).then(function(result) {
                return result.data;
            });
        }

        function setAttendance(attendanceList) {
            console.log(attendanceList);
            return $http.post(urlProvider.setAttendance(), attendanceList).then(function(result) {
                return result.data;
            });
        }

        //TODO
        function setRating(courseId, rating) {
            return $http.put(urlProvider.setRating(courseId, rating)).then(function(result) {
                return result.data;
            });
        }

        function subscribe(courseId) {
            return $http.post(urlProvider.subscribe(courseId)).then(function(result) {
                return result.data;
            });
        }

    }

})();