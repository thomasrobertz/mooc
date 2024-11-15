from datetime import datetime

from app.schema_repository.models import Connection, Location, Person
from app.schema_repository.schemas import (
    ConnectionSchema,
    LocationSchema,
    PersonSchema,
)

#from app.udaconnect.services import ConnectionService, LocationService, PersonService

from flask import request
from flask_accepts import accepts, responds
from flask_restx import Namespace, Resource
from typing import Optional, List

DATE_FORMAT = "%Y-%m-%d"

api = Namespace("UdaConnect-Connection", description="Connection CRUD services.")  # noqa

### REFACTORING TO NEW ARCHITECTURE

import grpc
from generated import connection_protocol_pb2;
from generated import connection_protocol_pb2_grpc;

@api.route("/persons/<person_id>/connection")
@api.param("start_date", "Lower bound of date range", _in="query")
@api.param("end_date", "Upper bound of date range", _in="query")
@api.param("distance", "Proximity to a given user in meters", _in="query")
class ConnectionDataResource(Resource):
    @responds(schema=ConnectionSchema, many=True)
    def get(self, person_id) -> ConnectionSchema:
        start_date: datetime = datetime.strptime(
            request.args["start_date"], DATE_FORMAT
        )
        end_date: datetime = datetime.strptime(request.args["end_date"], DATE_FORMAT)
        distance: Optional[int] = request.args.get("distance", 5)

        results = ConnectionService.find_contacts(
            person_id=person_id,
            start_date=start_date,
            end_date=end_date,
            meters=distance,
        )
                
        return results