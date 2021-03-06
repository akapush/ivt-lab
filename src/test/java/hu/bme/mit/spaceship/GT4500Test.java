package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {


  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;
  @BeforeEach
  public void init(){
    
    primary =  mock(TorpedoStore.class);
    secondary =  mock(TorpedoStore.class);
    this.ship = new GT4500(primary,secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Failure(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_FirstEmptyFireSecondary(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_PrimaryWasFired(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean resultFirst = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultSecond = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);

    // Assert
    assertEquals(true, resultFirst);
    assertEquals(true, resultSecond);
  }

}
