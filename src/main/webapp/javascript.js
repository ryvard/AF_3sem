
'use strict';
angular.module('resultModule', [])

        .controller('searchCntr', ['$scope', '$http', 'flights', 'reserveInfo', 'flightInfo', function ($scope, $http, flights, reserveInfo, flightInfo) {

                $scope.airlineInfo;
                $scope.departAirport;
                $scope.arrivalAirport;
                $scope.date;
                $scope.tickets;
                $scope.iataCodes;
                $scope.firstName;
                $scope.lastName;
                $scope.email;
                $scope.phone;
                $scope.departAirport = flights.flightInputs.depart;
                $scope.arrivalAirport = flights.flightInputs.arrival;
                $scope.date = flights.flightInputs.date;
                $scope.tickets = flights.flightInputs.ticket;
                $scope.firstName = reserveInfo.reserveInputs.firstName;
                $scope.lastName = reserveInfo.reserveInputs.lastName;
                $scope.email = reserveInfo.reserveInputs.email;
                $scope.phone = reserveInfo.reserveInputs.phone;
                
                $scope.getFlightInformation = function (info){
                   flightInfo.getFlightInfo(info);
                   console.log(info);
                };
                
                $scope.addReserveInformation = function (firstName, lastName, email, phone){
                    reserveInfo.addReserveInput(firstName, lastName, email, phone);
                    console.log(reserveInfo.reserveInputs.firstName);
                };

                $scope.addFlightInputs = function (departAirport, arrivalAirport, date, tickets) {
                    flights.addFlightInput(departAirport, arrivalAirport, date, tickets);
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

                flights.flightInputs = {};

                flights.addFlightInput = function (departAirport, arrivalAirport, date, tickets) {
                    flights.flightInputs = {depart: departAirport, arrival: arrivalAirport, date: date, ticket: tickets};
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
            
        .factory('flightInfo', [function(){
                var flightInfo = {};
        
       flightInfo.getFlightInfo = function (info){
           flightInfo.flightInformation = info;
           
       };
        
        return flightInfo;
        
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