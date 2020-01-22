package net.codejava.model;

public class Employee
{
    private int id;
    private String name;
    private double hourlyRate;
    private boolean partTime;

    public Employee(int id, String name, double hourlyRate, boolean partTime)
    {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.partTime = partTime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getHourlyRate()
    {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }

    public boolean isPartTime()
    {
        return partTime;
    }

    public void setPartTime(boolean partTime)
    {
        this.partTime = partTime;
    }

}
