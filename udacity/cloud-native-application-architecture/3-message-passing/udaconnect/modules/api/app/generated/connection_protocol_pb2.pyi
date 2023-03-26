from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class ConnectionMessage(_message.Message):
    __slots__ = ["distance", "end_date", "person_id", "start_date"]
    DISTANCE_FIELD_NUMBER: _ClassVar[int]
    END_DATE_FIELD_NUMBER: _ClassVar[int]
    PERSON_ID_FIELD_NUMBER: _ClassVar[int]
    START_DATE_FIELD_NUMBER: _ClassVar[int]
    distance: int
    end_date: str
    person_id: int
    start_date: str
    def __init__(self, person_id: _Optional[int] = ..., start_date: _Optional[str] = ..., end_date: _Optional[str] = ..., distance: _Optional[int] = ...) -> None: ...
