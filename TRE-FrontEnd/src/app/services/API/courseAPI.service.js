(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('courseAPI', courseAPI);

    /* @ngInject */
    function courseAPI($state, $http, urlProvider) {
        var courseAPI = {
            addLesson: addLesson,
            addParticipant: addParticipant,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            createCourse: createCourse,
            deleteLesson: deleteLesson,
            editCourse: editCourse,
            getAttachments: getAttachments,
            getComments: getComments,
            getCoursesForUser: getCoursesForUser,
            getEditedCourse: getEditedCourse,
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

        function getParticipants(courseId) {
            return $http.get(urlProvider.getParticipants(courseId)).then(function (result) {
                return result.data;
            });
        }

        function getShortInfo() {

        }

        function getTimetable(courseId) {
            return $http.get(urlProvider.getTimetable(courseId)).then(function (result) {
                return result.data;
            });
        }

        //////////

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

        function editCourse(courseId, courseData, isDraft, justEdit) {
            var fd = new FormData();
            if (isDraft) {
                console.log("Course just created");
                if (justEdit) {
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.editCourse(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }
                else {
                    console.log("admin approves course!");
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.confirm(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }

            }
            else {
                console.log("isnt draft");
                //courseData.additional = "";
                //courseData.dateTime = [""];
                //courseData.places = [""];

                fd.append('courseInfo', JSON.stringify(courseData));

                if (justEdit) {
                    console.log(courseData);
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.editCourse(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }
                else {
                    return $http.put(urlProvider.confirm(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course approved successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });

                }
            }
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

        function getEditedCourse(courseId) {
            return $http.get(urlProvider.getEditedCourse(courseId))
                .then(function (results) {
                    return results.data;
                });
        }

        //////////

        function addLesson(courseId, newDate, newPlace) {
            var lessonInfo = {
                date: newDate,
                place: newPlace
            };
            return $http.post(urlProvider.manageLesson(courseId), lessonInfo).then(function(result) {
                return result.data;
            });
        }

        function editLesson(courseId, lessonId, newDate, newPlace) {
            var lessonInfo = {
                id: lessonId,
                newDate: newDate,
                newPlace: newPlace
            };
            return $http.put(urlProvider.manageLesson(courseId), lessonInfo).then(function (result) {
                return result.data;
            });
        }

        function deleteLesson(courseId, lessonId) {
            return $http.delete(urlProvider.manageLesson(courseId), {prevLessonId: lessonId}).then(function (result) {
                return result.data;
            });
        }

        //////////

        function addParticipant(courseId, participantInfo) {
            return $http.post(urlProvider.addParticipant(courseId), participantInfo).then(function(result) {
                return result.data;
            });
        }

        //////////

        function getCoursesForUser(isActual, tagList) {
            return $http.get(urlProvider.getCoursesForUser(), {
                params: {
                    is_actual: isActual,
                    page: 1,
                    tag: []
                }
            }).then(function (results) {
                return results.data;
            });
        }

    }

})();