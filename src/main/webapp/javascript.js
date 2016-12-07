
'use strict';
angular.module('resultModule', [])

        .controller('searchCntr', ['$scope', '$http', 'flights', 'reserveInfo', function ($scope, $http, flights, reserveInfo) {

                $scope.airlineInfo;
                $scope.departAirport;
                $scope.arrivalAirport;
                $scope.date;
                $scope.tickets;
                $scope.flightInfo;
                $scope.iataCodes;
                $scope.firstName;
                $scope.lastName;
                $scope.email;
                $scope.phone; 
                $scope.departAirport = flights.inputs.depart;
                $scope.arrivalAirport = flights.inputs.arrival;
                $scope.date = flights.inputs.date;
                $scope.tickets = flights.inputs.ticket;
                $scope.firstName = reserveInfo.reserveInputs.firstName;
                $scope.lastName = reserveInfo.reserveInputs.lastName;
                $scope.email = reserveInfo.reserveInputs.email;
                $scope.phone = reserveInfo.reserveInputs.phone;
                
                $scope.addReserveInformation = function (firstName, lastName, email, phone){
                    reserveInfo.addReserveInput(firstName, lastName, email, phone);
                };

                $scope.addInputs = function (departAirport, arrivalAirport, date, tickets) {
                    flights.addInput(departAirport, arrivalAirport, date, tickets);
                };

                $scope.getFlights = function () {
                    if ($scope.arrivalAirport === 'all destinations')
                    {
                        $http.get('http://localhost:8080/AF_3sem/api/flights/'
                                + $scope.departAirport + '/' + $scope.date + '/' + $scope.tickets).then(function (response) {
                            $scope.airlineInfo = response.data;
                            console.log($scope);
                        }, function (error) {
                        });
                    } else {
                        $http.get('http://localhost:8080/AF_3sem/api/flights/'
                                + $scope.departAirport + '/' + $scope.arrivalAirport + '/' + $scope.date + '/' + $scope.tickets).then(function (response) {
                            $scope.airlineInfo = response.data;
                        }, function (error) {
                        });
                    }
                    ;
                };
                $scope.getFlights();
            }])

        .factory('flights', [function () {
                var flights = {};

                flights.inputs = {};

                flights.addInput = function (departAirport, arrivalAirport, date, tickets) {
                    flights.inputs = {depart: departAirport, arrival: arrivalAirport, date: date, ticket: tickets};
                };

                return flights;
            }])

        .factory('reserveInfo', [function () {
                var reserveInfo = {};

                reserveInfo.reserveInputs = {};

                    reserveInfo.addReserveInput = function (firstName, lastName, email, phone){
                        reserveInfo.reserveInputs = {firstName: firstName, lastName: lastName, email: email, phone: phone};
                    };

                            return reserveInfo;
                }])

            .filter('durationFilter', function () {
                return function (time) {
                    return (time / 60 | 0) + "h " + time % 60 + "m";
                };
            })
            .filter('arivalFilter', function () {
                return function (time, duration) {
                    var timeSplit = time.split(":");
                    return (parseInt(timeSplit[0]) + (duration / 60 | 0)) + ":" + (parseInt(timeSplit[1]) + duration % 60);
                };
            });




//                    $http.get('https://iatacodes.org/api/v6/airports?api_key=8a2623ff-1ca6-4250-aa10-838fb259775a').then(function (response) {
//                        $scope.iataCodes = response.data;
//                        console.log($scope.iataCodes);
//                    }, function (error) {
//
//                    });