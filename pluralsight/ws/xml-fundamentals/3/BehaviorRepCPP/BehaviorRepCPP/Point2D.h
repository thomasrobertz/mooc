#pragma once
class Point2D
{
private:
	double x_;
	double y_;
public:
	void MoveTo(double x, double y);
	double GetAngle();
	float GetRadius();
	Point2D(double x, double y);
	~Point2D(void);
};

